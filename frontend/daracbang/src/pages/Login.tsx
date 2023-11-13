import React, { useState } from "react";
import styled from "@emotion/styled";
import LoginHead from "../components/LoginHead";
import { Button, TextField } from "@mui/material";
import * as loginApi from "../api/memberApi";
import { AxiosResponse, isAxiosError } from "axios";
import { ResponseDataType } from "../api/responseType";
const CHARACTER_MAX_LIMIT = 10;
const CHARACTER_MIX_LIMIT = 2;

const LoginIn = () => {
  const [loginId, setLoginId] = useState("");
  const [password, setPassword] = useState("");
  const [loginIdValidate, setLoginIdValidate] = useState<boolean>(true);
  const handleIdChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setLoginId(event.target.value); // onChange를 위한 함수
  };

  // 패스워드 변경
  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const submitLogin = async () => {
    try {
      const data: AxiosResponse<{ jwt: string }> = await loginApi.login(loginId, password);
      console.log(data.data.jwt);
    } catch (error) {
      if (isAxiosError<ResponseDataType>(error)) {
        if (error.response?.status === 400) {
          alert("잘못된 값입니다.");
          return;
        }
        if (error.response?.status === 404 && error.response?.data.errorCode === "MEMBER_001") {
          alert("없는 계정입니다.");
          return;
        }
        if (error.response?.status === 409 && error.response?.data.errorCode === "MEMBER_004") {
          alert("잘못된 인증 정보입니다.");
          return;
        }
      }
      alert("서버 에러");
      console.error(error);
    }
  };
  return (
    <div>
      <LoginHead />
      <ContainerWrap>
        <ContentWrap>
          <TextField
            inputProps={{ minLength: 2, maxLength: 10 }}
            id="standard-basic"
            label="아이디"
            error={!loginIdValidate}
            variant="standard"
            style={{ margin: "20px" }}
            value={loginId}
            onChange={handleIdChange}
            helperText={""}
            InputProps={{ maxRows: CHARACTER_MAX_LIMIT, minRows: CHARACTER_MIX_LIMIT }}
          />
          <TextField
            inputProps={{ minLength: 4, maxLength: 20 }}
            id="standard-basic"
            label="비밀번호"
            variant="standard"
            style={{ margin: "20px" }}
            value={password}
            type="password"
            onChange={handlePasswordChange}
            InputProps={{ maxRows: 20, minRows: 4 }}
          />
        </ContentWrap>
        <ButtonWrap>
          <Button variant="outlined" onClick={submitLogin}>
            로그인
          </Button>
        </ButtonWrap>
      </ContainerWrap>
    </div>
  );
};

export default LoginIn;

const ContainerWrap = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 7%;
`;

const ContentWrap = styled.div`
  display: flex;
  flex-direction: row;
`;

const ButtonWrap = styled.div`
  margin-top: 80px;
`;
