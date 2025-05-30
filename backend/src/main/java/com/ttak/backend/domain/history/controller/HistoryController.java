package com.ttak.backend.domain.history.controller;

import java.util.List;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttak.backend.domain.history.dto.request.HistoryCount;
import com.ttak.backend.domain.history.dto.response.HistoryListRes;
import com.ttak.backend.domain.history.service.HistoryService;
import com.ttak.backend.global.auth.annotation.UserPk;
import com.ttak.backend.global.common.CommonResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
@Tag(name = "History API", description = "History 관련 API")
@CrossOrigin("*")
public class HistoryController {

	private final HistoryService historyService;

	@Operation(summary = "예시", description = "예시에 대한 설명")
	@PostMapping("/temp")
	public ResponseEntity<CommonResponse<?>> tmpMethod (){
		log.info("========== 임시 메소드 시작 ==========");

		log.info("========== 임시 메소드 종료 ==========");
		return ResponseEntity.ok(CommonResponse.success());
	}

	@Operation(summary = "공격 히스토리 반환", description = "공격 히스토리 리스트 반환")
	@GetMapping("/list")
	public ResponseEntity<CommonResponse<?>> listHistory (@UserPk final Long userId) {
		log.info("========== 히스토리 반환 시작 ==========");
		List<HistoryListRes> list = historyService.getAttackHistory(userId);
		log.info("========== 히스토리 반환 종료 ==========");
		return ResponseEntity.ok(CommonResponse.success(list));
	}

	@GetMapping("/pick-rank")
	public ResponseEntity<CommonResponse<?>> readPickForRank(@UserPk final Long userId) {
		HistoryCount hc = historyService.getCumulativeCounts(userId);
		return ResponseEntity.ok(CommonResponse.success(hc));
	}

	@GetMapping("/pick")
	public ResponseEntity<CommonResponse<?>> readUserPick(@UserPk final Long userId) {
		Long count = historyService.getUserPick(userId);
		Map<String , Long> message = new HashMap<>();
		message.put("myCount", count);
		return ResponseEntity.ok(CommonResponse.success(message));
	}

}
