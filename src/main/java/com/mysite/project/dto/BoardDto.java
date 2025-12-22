package com.mysite.project.dto;

import java.sql.Timestamp;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import com.mysite.project.model.Board;
import com.mysite.project.model.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

	private int id;
	private String title;
	private String content;
	private String filename; // DB 저장용
	private String filepath; // DB 저장용
	private int count; // 조회수
	private MultipartFile file; // ⭐ 실제 업로드 파일 받는 용도
	 
	private Timestamp createDate;

	@ManyToOne(fetch = FetchType.EAGER) // 기본전략
	@JoinColumn(name = "userId")
	private Users users;

	@Builder
	public BoardDto(int id, String title, String content, String filename, String filepath, Timestamp createDate, Users users) {
		this.id = id;
		this.title = title;
		this.content = content;
		
		this.filename= filename;
		this.filepath= filepath;
		this.createDate = createDate;
		this.users = users;
	}

	// toEntity()메서드를 통해 Service > Database(Entity)로 Data를 전달할 때 Dto를 통해서 전달
	public Board toEntity() { // save
		Board board = Board.builder()
				.id(id)
				.title(title)
				.content(content)
				.filename(filename)
				.filepath(filepath)
				.createDate(createDate)
				.users(users).build();
		return board;
	}

}