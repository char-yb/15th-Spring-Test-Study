package com.depromeet.yunbeom.post.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import com.depromeet.yunbeom.mock.TestContainer;
import com.depromeet.yunbeom.post.controller.response.PostResponse;
import com.depromeet.yunbeom.post.domain.PostCreate;
import com.depromeet.yunbeom.user.domain.User;
import com.depromeet.yunbeom.user.domain.UserStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

class PostCreateControllerTest {

	@Test
	void 사용자는_게시물을_작성할_수_있다() {
		// given
		TestContainer testContainer = TestContainer.builder()
			.clockHolder(() -> 1679530673958L)
			.build();
		testContainer.userRepository.save(User.builder()
			.id(1L)
			.email("uiurihappy@naver.com")
			.nickname("uiurihappy")
			.address("Seoul")
			.status(UserStatus.ACTIVE)
			.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
			.lastLoginAt(100L)
			.build());
		PostCreate postCreate = PostCreate.builder()
			.writerId(1)
			.content("helloworld")
			.build();

		// when
		ResponseEntity<PostResponse> result = testContainer.postCreateController.createPost(postCreate);

		// then
		assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
		assertThat(result.getBody()).isNotNull();
		assertThat(result.getBody().getContent()).isEqualTo("helloworld");
		assertThat(result.getBody().getWriter().getNickname()).isEqualTo("uiurihappy");
		assertThat(result.getBody().getCreatedAt()).isEqualTo(1679530673958L);
	}

}