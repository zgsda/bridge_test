package project.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import project.dto.PartnerContentDto;
import project.dto.PartnerDetailCommentDto;
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
	
	private PayListDto payListDto;
	
	private PartnerContentDto partnerContentDto;

	private PartnerDetailCommentDto partnerDetailCommentDto;
	
	// 작업 페이지 내 결제 내역 
	@GetMapping("/api/bridge/paylist/{plIdx}")
	public ResponseEntity<List<PayListDto>> openPayList(@PathVariable("plIdx") int plIdx) throws Exception {
		
		List<PayListDto> list = bridgeService.selectPayList(plIdx);
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	//파트너 협업창 게시글 조회
	@GetMapping("/api/bridge/partnerdetail/{pdIdx}")
	public ResponseEntity<List<PartnerContentDto>> openPartnerContentList(@PathVariable("pdIdx") int pdIdx) throws Exception {
		List<PartnerContentDto> list = bridgeService.selectPartnerContent(pdIdx);
		
		if(list != null && list.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	//파트너 협업창 게시글작성
	@PostMapping("/api/bridge/partnerdetail/write/{pdIdx}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> insertPartnetContent(
			@PathVariable int pdIdx,
			@RequestPart(value = "Data", required = false)PartnerContentDto partnerContentDto ,
			@RequestPart(value = "files", required = false) MultipartFile[] pcImg)
			throws Exception {
		String UPLOAD_PATH = "C:/temp/upload/";
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas"+pcImg);
//		PartnerContentDto partnerContentDto = new PartnerContentDto();
		try {
			for (MultipartFile mf : pcImg) {
				String originFileName = mf.getOriginalFilename();
				try {
					File f = new File(UPLOAD_PATH + originFileName);
					mf.transferTo(f);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
				partnerContentDto.setPcImg(originFileName);
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
			System.out.println("111111111111111" +result);
			System.out.println("222222222222" + pcImg);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}
	
	//파트너 협업창 게시글 수정
	@PutMapping("/api/bridge/partnerdetail/update/{pcIdx}")
	public ResponseEntity<Map<String, Object>> updatePartnerContent(
			@PathVariable int pcIdx,
			@RequestPart(value = "Data", required = false) PartnerContentDto partnerContentDto,
			@RequestPart(value = "files", required = false) MultipartFile[] pcImg)
			throws Exception {
		String UPLOAD_PATH = "C:/temp/upload/";

		try {
			for (MultipartFile mf : pcImg) {
				String originFileName = mf.getOriginalFilename();
				try {
					File f = new File(UPLOAD_PATH + originFileName);
					mf.transferTo(f);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
				partnerContentDto.setPcImg(originFileName);
			}

			int insertedCount = bridgeService.updatePartnerContent(partnerContentDto);

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
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}
	//파트너 협업창 게시글 삭제
	@PutMapping("/api/bridge/partnerdetail/delete/{pcIdx}")
	public ResponseEntity<Object> deletePartnerContent(@PathVariable("pcIdx") int pcIdx) throws Exception {
		try {
			bridgeService.deletePartnerContent(pcIdx);
			return ResponseEntity.status(HttpStatus.OK).body('Y');
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}
	//파트너 협업창 게시글의 댓글
		@GetMapping("/api/bridge/partnerdetail/comment/{pcIdx}")
			public ResponseEntity<List<PartnerDetailCommentDto>> openPartnerCommentList(@PathVariable("pcIdx") int pcIdx) throws Exception {
				List<PartnerDetailCommentDto> list = bridgeService.selectPartnerComment(pcIdx);
				
				return ResponseEntity.status(HttpStatus.OK).body(list);
		}
		
		//파트너 협업창 덧글작성
		@PostMapping("/api/bridge/partnerdetail/comment/{pdIdx}/{pcIdx}")
			public ResponseEntity<String> insertPartnerComment(
					@RequestBody PartnerDetailCommentDto PartnerDetailCommentDto) throws Exception {
				try {
					 bridgeService.insertPartnerComment(PartnerDetailCommentDto);
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("등록오류");
				}
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("정상처리");
			}
		
		//아이디 중복확인	
		@PostMapping("/api/idlist/{userId}" )
		public int userIdCheck(@PathVariable("userId")String userId) throws Exception {
			int result = bridgeService.userIdCheck(userId);
			return result;
		}
}

		