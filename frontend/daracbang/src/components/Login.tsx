import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import axios from 'axios';

export default function FormDialog() {
    const [open, setOpen] = React.useState(false);
    const [loginId, setLoginId] = React.useState("");
    const [password, setPassword] = React.useState("");


    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const loginIdHandle = (event: React.ChangeEvent<HTMLInputElement>) => {
        setLoginId(event.target.value);
    }

    const passwordHandle = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(event.target.value);
    }

    const clickLogin = () => {
        axios
            .post(`http://localhost:8080/api/members/login`, {
                loginId: loginId,
                password: password

            })
            .then((response) => {
                if (response.data) {
                    localStorage.setItem("accessToken", JSON.stringify(response.data.jwt));
                    window.location.replace("/daracbang");
                }

            })
            .catch((error) => {
                console.log(error);

                alert("아이디 및 비밀번호를 다시 확인해주세요.");
            });
    }

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
                    <Button onClick={clickLogin}>시작하기</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}