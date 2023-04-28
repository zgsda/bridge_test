package Repository;

import org.springframework.stereotype.Repository;

@Repository
public interface BridgeRepository {
	public int userIdCheck(String userId) throws Exception;
}