package project.service;

import java.util.List;


import project.dto.PartnerCommentDto;
import project.dto.PartnerContentDto;
import project.dto.PayListDto;

public interface BridgeService {

	public List<PayListDto> selectPayList(int plIdx) throws Exception;
	public List<PartnerContentDto> selectPartnerContent(int pdIdx) throws Exception;
	public List<PartnerCommentDto> selectPartnerComment(int pcIdx) throws Exception;
	void insertPartnerContent(PartnerContentDto PartnerContentDto) throws Exception;
	void updatePartnerContent(PartnerContentDto PartnerContentDto) throws Exception;
	void deletePartnerContent(int pcIdx) throws Exception;
}
