import React, { useRef, useState } from 'react';
import LoginHead from '../components/LoginHead';
import styled from "@emotion/styled";
import { Avatar, Button, TextField } from '@mui/material';
import DefaultImg from "../assets/images/defaultImg.png";
import axios from 'axios';

let formData = new FormData();

const SignUp = () => {
    const [profilefile, setProfileFile] = useState<any>(null);
    const [img, setImg] = useState(DefaultImg);
    const [loginId, setLoginId] = useState("");
    const [nickname, setNickname] = useState("");
    const [password, setPassword] = useState("");

    const imageUp = useRef<HTMLInputElement>(null);

    const onClickImage = () => {
        if (imageUp.current) {
            imageUp.current.click();
        }

    };


    // 이미지 파일 변경
    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        formData = new FormData();
        const file = e.target.files && e.target.files[0];

        if (file) {

            setImg(URL.createObjectURL(file));
            setProfileFile(file);
            for (const key of formData.keys()) {
                console.log(key);
            }
            for (const value of formData.values()) {
                console.log(value);
            }
        }
    };

    // 아이디 변경
    const handleIdChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setLoginId(event.target.value);
    };


    // 아이디 중복 확인 함수
    const checkLoginId = () => {
        axios
            .get(`http://localhost:8080/api/members/login-id/${loginId}`) 
            .then((response) => {
                alert("사용 가능한 아이디입니다.");
                setLoginId(loginId);
            })
            .catch((error) => {
                console.log(error);
                alert("중복된 아이디입니다."); 
            });
    };
    

    // 닉네임 변경
    const handleNicknameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setNickname(event.target.value);
    };



    // 닉네임 중복 확인 함수
    const checkNickname = () => {
        axios
            .get(`http://localhost:8080/api/members/nickname/${nickname}`)
            .then((response) => {
                alert("사용 가능한 닉네임입니다.");
                setNickname(nickname);
            })
            .catch((error) => {
                console.log(error);
                alert("중복된 닉네임입니다");
            });
    };


    // 패스워드 변경
    const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(event.target.value);
    };

    const CHARACTER_MAX_LIMIT = 10;
    const CHARACTER_MIX_LIMIT = 2;

    const loginIdValidation = () => {
        let check = /[~!@#$%^&*()_+|<>?:{}.,/;='"ㄱ-ㅎ | ㅏ-ㅣ |가-힣]/;
        const comment = "알파벳,숫자 포함하여 8자리로 설정해주세요";

        if (loginId.length < 2 && check.test(loginId)) {
            return comment;
        }
        return null;
    };


    const nicknameValidation = () => {
        let check = /[~!@#$%^&*()_+|<>?:{}.,/;='"ㄱ-ㅎ | ㅏ-ㅣ |가-힣]/;
        const comment = "알파벳,숫자 포함하여 8자리로 설정해주세요";

        if (nickname.length < 2 && check.test(nickname)) {
            return comment;
        }
        return null;
    };



    const handleSubmit = (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
        event.preventDefault();

        console.log("nickName :" + nickname);
        console.log("loginId :" + loginId);
        console.log("password :" + password);
        console.log("img :" + profilefile);

        formData.append("loginId", loginId);
        formData.append("nickname", nickname);
        formData.append("password", password);
        formData.append("image", profilefile);

        axios
            .post(`http://localhost:8080/api/members`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            })
            .then((response) => {
                console.log(response.data);
                window.location.replace("/daracbang");
            })
            .catch((error) => {
                // 오류 처리
                alert("회원가입에 실패했습니다. 아이디, 비밀번호, 닉네임 설정을 다시 확인해주세요.");
                console.log(Error);
            });
    };



    return (
        <div>
            <LoginHead />
            <ContainerWrap>
                <ContentWrap>
                    <AddImgWrap>
                        <input
                            type="file"
                            style={{ display: "none" }}
                            ref={imageUp}
                            onChange={handleFileChange}
                        />
                        {img && (
                            <Avatar
                                onClick={onClickImage}
                                alt="Default Img"
                                src={img}
                                style={{ width: 300, height: 300 }}
                            ></Avatar>
                        )}
                    </AddImgWrap>

                    <InfoWrap>
                        <div>
                            <TextField
                                inputProps={{ minLength: 2, maxLength: 10 }}
                                id="standard-basic" label="아이디" variant="standard"
                                style={{ margin: "20px" }}
                                value={loginId}
                                onChange={handleIdChange}
                                helperText={
                                    loginIdValidation() ? "알파벳,숫자 포함하여 8자리로 설정해주세요" : ""
                                }
                                InputProps={{ maxRows: CHARACTER_MAX_LIMIT, minRows: CHARACTER_MIX_LIMIT }}

                            />
                            <Button
                                style={{ width: "90px", marginLeft: "10px", height: "40px", marginTop: "20px" }}
                                variant="contained"
                                onClick={checkLoginId}
                            >
                                중복확인
                            </Button>
                        </div>
                        <TextField
                            inputProps={{ minLength: 4, maxLength: 20 }}
                            id="standard-basic" label="비밀번호" variant="standard"
                            style={{ margin: "20px" }}
                            value={password}
                            onChange={handlePasswordChange}
                            InputProps={{ maxRows: 20, minRows: 4 }}

                        />
                        <div >
                            <TextField
                                inputProps={{ minLength: 2, maxLength: 10 }}
                                id="standard-basic" label="닉네임" variant="standard"
                                style={{ margin: "20px" }}
                                value={nickname}
                                onChange={handleNicknameChange}
                                helperText={
                                    nicknameValidation() ? "알파벳,숫자 포함하여 8자리로 설정해주세요" : ""
                                }
                                InputProps={{ maxRows: CHARACTER_MAX_LIMIT, minRows: CHARACTER_MIX_LIMIT }}
                            />
                            <Button
                                style={{ width: "90px", marginLeft: "10px", height: "40px", marginTop: "20px" }}
                                variant="contained"
                                onClick={checkNickname}
                            >
                                중복확인
                            </Button>
                        </div>

                    </InfoWrap>
                </ContentWrap>
                <ButtonWrap>
                    <Button variant="outlined" onClick={handleSubmit}>
                        시작하기
                    </Button>
                </ButtonWrap>
            </ContainerWrap>

        </div>
    );
};

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

const AddImgWrap = styled.div`
  background-color: #green;
  margin-right: 80px; 
`;

const InfoWrap = styled.div`
    display: flex;
    flex-direction: column;
`;

const ButtonWrap = styled.div`
  margin-top: 80px;
`;


export default SignUp;