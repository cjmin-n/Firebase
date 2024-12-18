package org.example.firebase_server;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class FirebaseServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirebaseServerApplication.class, args);
	}


	/**
	 * Firebase 를 초기화하는 메소드
	 * Spring Boot 어플리케이션이 시작될 때 실행된다.
	 * Firebase Admin Sdk 를 설정하고 FirebaseApp 을 초기화한다.
	 * */

	// 메인이 실행되고 나서 한번만 실행되서 Firebase 실행할 준비를 하라는 어노테이션
	@PostConstruct
	public void initFirebase(){
		try {
			FileInputStream serviceAccount = new FileInputStream(
					"src/main/resources/fir-test-app-91e51-firebase-adminsdk-g4aw0-45f9777b36.json"
			);
			// FirebaseOptions 객체를 생성하여 초기화하기 위한 옵션을 설정한다.
			FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
			// Firebase 앱을 초기화한다. 초기화가 성공하면 Firebase 에서 제공하는 서비스에 접근할 수 있다.
			FirebaseApp.initializeApp(options);
			System.out.println("파이어베이스 초기화 완료");
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없음");
            throw new RuntimeException(e);
        } catch (IOException e) {
			System.out.println("초기화 실패");
            throw new RuntimeException(e);
        }

    }
}
