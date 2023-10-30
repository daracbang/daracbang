import React from 'react';
import LoginHead from '../components/LoginHead';
import styled from "@emotion/styled";
import { Avatar, Button, TextField } from '@mui/material';
import DefaultImg from "../assets/images/defaultImg.png";

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
                                sx={{ width: 300, height: 300 }}
                            ></Avatar>
                        )}
                    </AddImgWrap>

                    <InfoWrap>
                        <TextField
                            id="standard-basic" label="Standard" variant="standard"
                            style={{ margin: "10px" }}
                        />
                        <TextField
                            id="standard-basic" label="Standard" variant="standard"
                            style={{ margin: "10px" }}
                        />
                        <div>
                            <TextField
                                id="standard-basic" label="Standard" variant="standard"
                                style={{ margin: "10px" }}
                            />
                            <Button
                                sx={{ width: "100px", marginLeft: "10px", height: 52 }}
                                variant="contained"
                                color="success"
                            >
                                중복확인
                            </Button>
                        </div>

                    </InfoWrap>
                </ContentWrap>
                <ButtonWrap>
                    <Button variant="outlined">
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
  margin-top: 10%;
`;

const ContentWrap = styled.div`
    display: flex;
    flex-direction: row;
`;

const AddImgWrap = styled.div`
  background-color: #green;
`;

const InfoWrap = styled.div`
    display: flex;
    flex-direction: column;
`;

const ButtonWrap = styled.div`
  margin-top: 10px;
`;


export default SignUp;