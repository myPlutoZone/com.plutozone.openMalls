CREATE SEQUENCE SQ_SEQ_BSK;
CREATE SEQUENCE SQ_SEQ_BBS;
CREATE SEQUENCE SQ_SEQ_MBR;
CREATE SEQUENCE SQ_SEQ_SLL;
CREATE SEQUENCE SQ_SEQ_MNG;
CREATE SEQUENCE SQ_SEQ_PRD;
CREATE SEQUENCE SQ_SEQ_SLE;
CREATE SEQUENCE SQ_SEQ_BUY_MST;
CREATE SEQUENCE SQ_SEQ_BUY_DTL;
CREATE SEQUENCE SQ_SEQ_PAY;
CREATE SEQUENCE SQ_SEQ_TRM_AGR;
CREATE SEQUENCE SQ_SEQ_SLE_IMG;

CREATE INDEX IDX_TB_MBR_LOGIN ON TB_MBR_MST(EMAIL, CD_STATE);

-- 참고 1) 탈퇴한 회원의 정보는
--			- 처리 방안 1) 백업 후 개인 정보는 모두 NULL 또는 "" 또는 삭제 처리
-- 			- 처리 방안 2) 탈퇴한 이메일을 수정(예: update tb_mbr_mst set email = '#' || seq_mbr || '_' || email where seq_mbr = 6;)
-- 참고 2) 가입 시 이메일 인증 시간 초과 시
-- 			- 처리 방안 1) 기존 이메일에 기존 SEQ_MBR을 추가(예: update tb_mbr_mst set email = '#' || seq_mbr || '_' || email where seq_mbr = 6;)
ALTER TABLE TB_MBR_MST ADD CONSTRAINT UK1_TB_MBR UNIQUE(EMAIL);

-- for Oracle 11G
-- alter session set "_ORACLE_SCRIPT"=true;