package project.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import project.dto.PartnerContentDto;
import project.dto.PartnerDetailCommentDto;
import project.dto.PartnerDetailDto;
import project.dto.PayListDto;
import project.security.JwtTokenUtil;
import project.service.BridgeService;

@Slf4j
@RestController
public class bridgeApiController {

	@Autowired
	private BridgeService bridgeService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
//	
	private PayListDto payListDto;
	private PartnerContentDto partnerContentDto;

	// 2. 파트너 협업창 게시글 조회
	@GetMapping("/api/bridge/partnerdetail/{pdIdx}")
	public ResponseEntity<List<PartnerContentDto>> openPartnerContentList(@PathVariable("pdIdx") int pdIdx)
			throws Exception {
		List<PartnerContentDto> list = bridgeService.selectPartnerContent(pdIdx);

		if (list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	// 3. 파트너 협업창 게시글 상세조회
	@GetMapping("api/bridge/partnerdetail/content/{pcIdx}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> openPartnerContentDetail(@PathVariable("pcIdx") int pcIdx)
			throws Exception {
		PartnerContentDto partnerContentDto = bridgeService.selectPartnerContentDetail(pcIdx);
		Map<String, Object> result = new HashMap<>();
		result.put("pcContent", partnerContentDto);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	// 4. 파트너 협업창 게시글 등록
	@PostMapping("/api/bridge/partnerdetail/write/{pdIdx}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> insertPartnetContent(@PathVariable int pdIdx,
			@RequestPart(value = "Data", required = false) PartnerContentDto partnerContentDto,
			@RequestPart(value = "files", required = false) MultipartFile[] pcImg) throws Exception {
		String UPLOAD_PATH = "C:/temp/upload/";
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas" + pcImg);
//			PartnerContentDto partnerContentDto = new PartnerContentDto();
		try {
			for (MultipartFile mf : pcImg) {
				String originFileName = mf.getOriginalFilename();
				try {
					File f = new File(UPLOAD_PATH + originFileName);
					mf.transferTo(f);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
				partnerContentDto.setPcFile(originFileName);
			}
			System.out.println("aaaaaaaaaaaaaaaaaaaasssssssssssssssssss" + partnerContentDto);

			int insertedCount = bridgeService.insertPartnerContent(partnerContentDto);

			if (insertedCount > 0) {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "정상적으로 등록되었습니다.");
				result.put("count", insertedCount);
				result.put("pcIdx", partnerContentDto.getPcIdx());
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} else {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "등록된 내용이 없습니다.");
				result.put("count", insertedCount);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
		} catch (Exception e) {
			Map<String, Object> result = new HashMap<>();
			System.out.println(e);
			result.put("message", "등록 중 오류가 발생했습니다.");
			System.out.println("111111111111111" + result);
			System.out.println("222222222222" + pcImg);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}

	// 5. 파트너 협업창 게시글 수정
	@PutMapping("/api/bridge/partnerdetail/update/{pcIdx}")
	public ResponseEntity<Map<String, Object>> updatePartnerContent(@PathVariable("pcIdx") int pcIdx,
			@RequestPart(value = "Data", required = false) PartnerContentDto partnerContentDto,
			@RequestPart(value = "files", required = false) MultipartFile[] pcImg) throws Exception {
		String UPLOAD_PATH = "C:/temp/upload/";
		int insertedCount = 0;
		partnerContentDto.setPcIdx(pcIdx);
		try {
			if (pcImg != null) {
				for (MultipartFile mf : pcImg) {
					String originFileName = mf.getOriginalFilename();
					try {
						File f = new File(UPLOAD_PATH + originFileName);
						mf.transferTo(f);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					}
					partnerContentDto.setPcFile(originFileName);
				}
				insertedCount = bridgeService.updatePartnerContent(partnerContentDto);

			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + partnerContentDto);

			if (insertedCount > 0) {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "정상적으로 등록되었습니다.");
//							result.put("count", insertedCount);
				result.put("pcContent", partnerContentDto.getPcContent());
				result.put("pcIdx", partnerContentDto.getPcIdx());
				result.put("pcImg", partnerContentDto.getPcFile());
				System.out.println("1111111111111111111111111");
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} else {
				Map<String, Object> result = new HashMap<>();
				result.put("message", "등록된 내용이 없습니다.");
				result.put("count", insertedCount);
				System.out.println("222222222222222");
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
		} catch (Exception e) {
			Map<String, Object> result = new HashMap<>();
			System.out.println(e);
			result.put("message", "등록 중 오류가 발생했습니다.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}

	// 6. 파트너 협업창 게시글 삭제
	@PutMapping("/api/bridge/partnerdetail/delete/{pcIdx}")
	public ResponseEntity<Object> deletePartnerContent(@PathVariable("pcIdx") int pcIdx) throws Exception {
		try {
			bridgeService.deletePartnerContent(pcIdx);
			return ResponseEntity.status(HttpStatus.OK).body('Y');
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}

	// 7. 파트너 협업창 결제 내역
	@GetMapping("/api/bridge/partnerDetail/paylist/{userId1}/{userId2}")
	public ResponseEntity<PayListDto> openPayList(@PathVariable("userId1") String userId1,
			@PathVariable("userId2") String userId2) throws Exception {
		PayListDto payListDto = new PayListDto();

		payListDto.setUserId1(userId1);
		payListDto.setUserId2(userId2);

		PayListDto payListDto1 = bridgeService.selectPayList(payListDto);

		if (payListDto1 != null) {
			return ResponseEntity.status(HttpStatus.OK).body(payListDto1);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// 8. 파트너 협업창 작업목록 조회
	@GetMapping("/api/bridge/partnerDetail/projectList/{userId1}")
	public ResponseEntity<List<PartnerDetailDto>> openProjectList(@PathVariable("userId1") String userId1) {
		try {

			List<PartnerDetailDto> list1 = bridgeService.selectProjectList(userId1);

			if (list1.size() != 0) {
				return ResponseEntity.status(HttpStatus.OK).body(list1);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// 9. 파트너 협업창 게시글의 댓글 조회
	@GetMapping("/api/bridge/partnerDetail/comment/{pcIdx}")
	public ResponseEntity<List<PartnerDetailCommentDto>> openPartnerCommentList(@PathVariable("pcIdx") int pcIdx)
			throws Exception {

		try {

			List<PartnerDetailCommentDto> list = bridgeService.selectPartnerComment(pcIdx);

			if (list.size() != 0) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// 10. 파트너 협업창 게시글의 댓글 작성
	@PostMapping("/api/bridge/partnerDetail/comment/write/{pcIdx}")
	public ResponseEntity<Integer> addComment(@RequestBody PartnerDetailCommentDto partnerDetailCommentDto)
			throws Exception {

		int insertCommentCount = bridgeService.insertPartnerComment(partnerDetailCommentDto);

		if (insertCommentCount != 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(insertCommentCount);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(insertCommentCount);
		}

	}

}
