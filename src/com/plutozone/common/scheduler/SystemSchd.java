/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.openMalls
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: SystemSchd.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20241028115342][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.common.scheduler;

// import java.text.SimpleDateFormat;
// import java.util.Calendar;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-10-28
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class SystemSchd {
	
	/** Logger */
	// private static Logger logger = LoggerFactory.getLogger(SystemSchd.class);
	
	/**
	[필드]
	---------------------------------------------------------------------
	NO		필드 이름			허용 값					허용된 특수 문자
	---------------------------------------------------------------------
	1		Seconds				0 ~ 59					, - * /
	2		Minutes				0 ~ 59					, - * /
	3		Hours				0 ~ 23					, - * /
	4		Day-of-Month		1 ~ 31					, - * ? / L W
	5		Month				1 ~12 or JAN ~ DEC		, - * /
	6		Day-Of-Week			1 ~ 7 or SUN-SAT		, - * ? / L #
	-		Year(optional)		empty, 1970 ~ 2099		, - * /
	
	[특수문자]
	'*' 	: 모든 수를 표시(예: 분의 위치에 * 설정하면 "매 분마다" 실행)
	'?' 	: Day-of-Month 와 Day-of-Week 필드에서만 사용가능하며 특별한 값이 없음을 표시
	'-' 	: 기간 설정(예: 시간 필드에 "10-12"이라 입력하면 "10, 11, 12시에만" 실행)
	',' 	: MON, WED, FRI와 같이 특정 시간 설정(예: "MON,WED,FRI"이면 "월,수,금"에만 실행)
	'/' 	: 증가 표현(예: 초 단위에 "0/15"로 세팅되면 0초부터 시작하여 15초 이후에 실행)
	'L' 	: Day-of-Month와 Day-of-Week 필드에만 사용하며 마지막날을 표시(예: Day-of-Month에 "L" 로 되어 있다면 이번 달의 마지막에 실행)
	'W' 	: Day-of-Month 필드에만 사용되며 주어진 기간에 가장 가까운 평일(월~금)을 표시
			: (예: 만약 "15W"일때
			: 이번 달의 15일이 토요일이라면 가장 가까운 평일인 14일 금요일날 실행
			: vs. 이번 달의 15일이 일요일이라면 가장 가까운 평일인 16일 월요일에 실행
			: vs. 이번 달의 15일이 화요일이라면 화요일인 15일에 실행)
	"LW" 	: L과 W를 결합하여 사용할 수 있으며 "LW"는 "이번달 마지막 평일"을 표시
	"#" 	: Day-of-Week에만 사용(예: "6#3" 이면 3(3)번째 주 금요일(6)에 실행, 1은 일요일 ~ 7은 토요일)
	
	[예]
	"0 0 12 * * ?"			매일 12시에 실행
	"0 15 10 ? * *"			매일 10시 15분에 실행
	"0 15 10 * * ?"			매일 10시 15분에 실행
	"0 15 10 * * ? *"		매일 10시 15분에 실행
	"0 15 10 * * ? 2010"	2010년 동안 매일 10시 15분에 실행
	"0 * 14 * * ?"			매일 14시에서 시작해서 14:59분에 끝남
	"0 0/5 14 * * ?"		매일 14시에 시작하여 5분 간격으로 실행되며 14:55분에 끝남
	"0 0/5 14,18 * * ?"		매일 14시에 시작하여 5분 간격으로 실행되며 14:55분에 끝남 + 매일 18시에 시작하여 5분 간격으로 실행되며 18:55분에 끝남
	"0 0-5 14 * * ?"		매일 14시에 시작하여 14:05 분에 끝남
	*/
	@Scheduled(cron="0/10 * * * * *")
	public void schedule_10s() {
		/*
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.debug("[" + this.getClass().getName() + ".schedule_10s()] " + "Run the scheduler every 10 seconds: " + dateFormat.format(calendar.getTime()));
		*/
	}
}
