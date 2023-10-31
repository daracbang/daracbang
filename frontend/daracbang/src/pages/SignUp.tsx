import React from 'react';
import LoginHead from '../components/LoginHead';
import styled from "@emotion/styled";
import { Avatar, Button, TextField } from '@mui/material';
import DefaultImg from "../assets/images/defaultImg.png";
import { Link } from 'react-router-dom';

const SignUp = () => {


    return (
        <div>
            <LoginHead />
            <ContainerWrap>
                <ContentWrap>
                    <AddImgWrap>
                        <input
                            type="file"
                            style={{ display: "none" }}
                        />
                        {DefaultImg && (
                            <Avatar
                                alt="Default Img"
                                src={DefaultImg}
                                style={{ width: 300, height: 300 }}
                            ></Avatar>
                        )}
                    </AddImgWrap>

                    <InfoWrap>
                        <TextField
                            id="standard-basic" label="아이디" variant="standard"
                            style={{ margin: "20px" }}
                        />
                        <TextField
                            id="standard-basic" label="비밀번호" variant="standard"
                            style={{ margin: "20px" }}
                        />
                        <div >
                            <TextField
                                id="standard-basic" label="닉네임" variant="standard"
                                style={{ margin: "20px" }}
                            />
                            <Button
                                style={{ width: "90px", marginLeft: "10px", height: "40px", marginTop: "20px" }}
                                variant="contained"
                                color="success"
                            >
                                중복확인
                            </Button>
                        </div>

                    </InfoWrap>
                </ContentWrap>
                <ButtonWrap>
                    <Link to={"/daracbang"}>
                        <Button variant="outlined">
                            시작하기
                        </Button>
                    </Link>

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