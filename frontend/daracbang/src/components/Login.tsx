import * as React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import { AxiosResponse, isAxiosError } from "axios";

import * as tokenUtil from "../utils/tokenUtil";
import * as loginApi from "../api/memberApi";
import { ResponseDataType } from "../api/responseType";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { loginAction } from "../store/memberReducer";
export default function FormDialog() {
  const [open, setOpen] = React.useState(false);
  const [loginId, setLoginId] = React.useState("");
  const [password, setPassword] = React.useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const loginIdHandle = (event: React.ChangeEvent<HTMLInputElement>) => {
    setLoginId(event.target.value);
  };

  const passwordHandle = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const submitLogin = async () => {
    if (!validateInputValues()) {
      return;
    }
    try {
      const data: AxiosResponse<{ jwt: string }> = await loginApi.login(loginId, password);
      tokenUtil.saveToken(data.data.jwt);
      const response = await loginApi.getMyMemberInfo();
      const memberInfo = response.data;
      dispatch(loginAction(memberInfo));
      alert("로그인 성공!");
      navigate(`/daracbang/${memberInfo.id}`);
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

  const validateInputValues = () => {
    if (loginId.trim().length === 0 || password.trim().length === 0) {
      alert("값을 정확히 입력해주세요");
      return false;
    }
    return true;
  };
  return (
    <div>
      <Button variant="outlined" onClick={handleClickOpen}>
        로그인
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>로그인</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            label="아이디"
            value={loginId}
            onChange={loginIdHandle}
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            label="비밀번호"
            value={password}
            onChange={passwordHandle}
            type="password"
            fullWidth
            variant="standard"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>취소</Button>
          <Button onClick={submitLogin}>시작하기</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
