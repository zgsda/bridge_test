package project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import project.dto.PartnerContentDto;
import project.dto.PartnerDetailCommentDto;
import project.dto.PartnerDetailDto;
import project.dto.PayListDto;
import project.dto.ProjectListDto;
import project.dto.UserProfileDto;
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

	// 1. 파트너 협업창 결제 내역
	@GetMapping("/api/bridge/paylist/{userId1}/{userId2}")
	public ResponseEntity<List<PayListDto>> openPayList(@PathVariable("userId1") String userId1,
			@PathVariable("userId2") String userId2) throws Exception {
		PayListDto payListDto = new PayListDto();
		payListDto.setUserId1(userId1);
		payListDto.setUserId2(userId2);

		List<PayListDto> list = bridgeService.selectPayList(payListDto);

		if (!list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
		}
	}

	// 2. 파트너 협업창 작업목록 조회
	@GetMapping("/api/bridge/projectList/{userId1}")
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


	// 3. 파트너 협업창 게시글의 댓글 조회
	@GetMapping("/api/bridge/partnerdetail/{pcIdx}")
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

}
