package project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Parameter;
import project.dto.PartnerCommentDto;
import project.dto.PartnerContentDto;
import project.dto.PayListDto;
import project.security.JwtTokenUtil;
import project.service.BridgeService;

@RestController
public class bridgeApiController {
	
	@Autowired
	private BridgeService bridgeService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private PayListDto payListDto;
	
	private PartnerContentDto PartnerContentDto;

	private PartnerCommentDto PartnerCommentDto;
	
	// 작업 페이지 내 결제 내역 
	@GetMapping("/api/bridge/paylist/{plIdx}")
	public ResponseEntity<List<PayListDto>> openPayList(@PathVariable("plIdx") int plIdx) throws Exception {
		
		List<PayListDto> list = bridgeService.selectPayList(plIdx);
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	//파트너 협업창 게시글 
	@GetMapping("/api/bridge/partnerdetail/{pdIdx}")
	public ResponseEntity<List<PartnerContentDto>> openPartnerContentList(@PathVariable("pdIdx") int pdIdx) throws Exception {
		List<PartnerContentDto> list = bridgeService.selectPartnerContent(pdIdx);
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	//파트너 협업창 게시글의 댓글
	@GetMapping("/api/bridge/partnerdetail/{pdIdx}/{pcIdx}")
		public ResponseEntity<List<PartnerCommentDto>> openPartnerCommentList(@PathVariable("pcIdx") int pcIdx) throws Exception {
			List<PartnerCommentDto> list = bridgeService.selectPartnerComment(pcIdx);
			
			return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	//파트너 협업창 게시글작성
	@PostMapping("/api/bridge/partnerdetail")
	public ResponseEntity<String> insertPartnerContent(
			@Parameter(description="a", required=true)
			@RequestBody PartnerContentDto PartnerContentDto) throws Exception {
		try {
			bridgeService.insertPartnerContent(PartnerContentDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록오류");
		}
		return ResponseEntity.status(HttpStatus.OK).body("정상처리");
	}
	//파트너 협업창 게시글 수정
	@PutMapping("/api/bridge/partnerdetail/{pcIdx}")
	public ResponseEntity<Object> updatePartnerContent(@PathVariable("pcIdx") int pcIdx, @RequestBody PartnerContentDto PartnerContentDto) throws Exception {
		try {
			PartnerContentDto.setPcIdx(pcIdx);
			bridgeService.updatePartnerContent(PartnerContentDto);
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}
	//파트너 협업창 게시글 삭제
	@DeleteMapping("/api/bridge/partnerdetail/{pcIdx}")
	public ResponseEntity<Object> deletePartnerContent(@PathVariable("pcIdx") int pcIdx) throws Exception {
		try {
			bridgeService.deletePartnerContent(pcIdx);
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}
}
		