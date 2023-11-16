import React, { useState } from 'react';
import Head from '../components/Head';
import styled from "@emotion/styled";
import Dial from '../components/SpeedDial';
import { Button, Card, TextField, ThemeProvider, Typography, createTheme } from '@mui/material';
import Search from '../assets/images/search.png';
import SearchNeigh from '../components/SearchNeigh';
import AddMusic from '../components/AddMusic';
import { useSelector } from 'react-redux';
import { RootState } from '../store/rootReducer';
import FriendNeigh from '../components/FriendNeigh';
import RequestNeigh from '../components/RequestNeigh';
import { MemberInfo } from '../store/memberReducer';
import axios from 'axios';
import { getToken } from '../utils/tokenUtil';

const Neighbor = () => {

    const theme = createTheme({
        typography: {
            fontFamily: "KyoboHand"
        },
    });

    const member = useSelector((state: RootState) => {
        return state.memberReducer.member;
    });


    const [searchedNickname, setSearchedNickname] = useState<string>();

    const [requestMember, setRequestMember] = useState<MemberInfo[]>([]);
    const [friendMember, setFriendMember] = useState<MemberInfo[]>([]);

    const accessToken = getToken();



    React.useEffect(() => {


        axios
            .get(`/api/neighbors/accepts`, {

                headers: {
                    Authorization: `Bearer ${accessToken}`,
                },
            })
            .then((response) => {
                console.log(response.data);
                setRequestMember(response.data); // API 호출 완료 후에 studyrooms 업데이트
                console.log(requestMember);
            })
            .catch((error) => {
                // 오류 처리
                console.log(error);
            });

        axios
            .get(`/api/neighbors`, {

                headers: {
                    Authorization: `Bearer ${accessToken}`,
                },
            })
            .then((response) => {
                console.log(response.data);
                setFriendMember(response.data); // API 호출 완료 후에 studyrooms 업데이트
                console.log(friendMember);
            })
            .catch((error) => {
                // 오류 처리
                console.log(error);
            });


    },);


    return (
        <div>
            <Head />
            <ContainerWrap style={{ backgroundColor: "#F2EBEB" }}>
                <ContentWrap>
                    <Card style={{ height: "600px", width: "420px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "100px", boxShadow: "3px 3px 2px 1px #bdbdbd", borderRadius: "15px" }} >
                        <ThemeProvider theme={theme} >
                            <img src={Search} alt='search' style={{ marginTop: "15px", marginLeft: "15px" }} />
                            <TextField value={searchedNickname}
                                onChange={(e) => setSearchedNickname(e.target.value)} variant="standard" style={{ width: "300px", marginLeft: "20px", marginRight: "10px", marginTop: "15px", fontFamily: "KyoboHand" }} />
                        </ThemeProvider>
                        {searchedNickname && <SearchNeigh searchs={searchedNickname} />}

                        <Card style={{ height: "400px", width: "400px", margin: "10px", marginTop: "30px", boxShadow: "3px 3px 2px 1px #bdbdbd", borderRadius: "15px" }} >
                            {requestMember.map((requests) => (
                                <RequestNeigh requests={requests} />
                            ))}
                        </Card>
                    </Card>

                    <CardWrap>

                        <Card style={{ height: "150px", marginLeft: "50px", marginBottom: "10px", boxShadow: "3px 3px 2px 1px #bdbdbd", borderRadius: "15px", display: "flex", flexDirection: "column" }} >
                            <ThemeProvider theme={theme} >
                                <TextField value={member?.introduce} multiline style={{ fontFamily: "KyoboHand", marginTop: "15px", marginLeft: "30px", width: "360px" }} rows={2}></TextField>
                            </ThemeProvider>
                            <Button variant='outlined' style={{ marginLeft: "300px", marginTop: "10px", width: "90px", height: "30px" }}>수정하기</Button>
                        </Card>
                        <Card style={{ height: "440px", width: "420px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "50px", boxShadow: "3px 3px 2px 1px #bdbdbd", borderRadius: "15px" }} >
                            <Typography style={{ fontFamily: "omyu_pretty", textAlign: "center", fontWeight: "bold", fontSize: "20px", marginTop: "5px" }}>나의 BFF</Typography>
                            {friendMember.map((friends) => (
                                <FriendNeigh friends={friends} />
                            ))}

                        </Card>
                    </CardWrap>

                    <Card style={{ height: "410px", width: "420px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "50px", boxShadow: "3px 3px 2px 1px #bdbdbd", borderRadius: "15px" }} >
                        <SearchBar style={{ marginTop: "15px", marginLeft: "30px" }}>
                            <ThemeProvider theme={theme} >
                                <TextField variant="standard" style={{ width: "270px", marginRight: "10px", fontFamily: "KyoboHand" }}></TextField>
                            </ThemeProvider>
                            <Button variant='outlined' size='small'>추가하기</Button>
                        </SearchBar>
                        <AddMusic />
                        <AddMusic />
                        <AddMusic />
                        <AddMusic />
                        <AddMusic />
                    </Card>

                </ContentWrap>
                <Navi style={{ transform: 'translateZ(0px)', flexGrow: 1 }}>
                    <Dial />
                </Navi>
            </ContainerWrap>


        </div >
    );
};

const ContainerWrap = styled.div`
    display: flex;
    flex-direction: row;
    padding-top: 27px;
    padding-bottom: 40px;
`;

const ContentWrap = styled.div`
    display: flex;
    flex-direction: row;
`;

const Navi = styled.div`

`;

const CardWrap = styled.div`
    display: flex;
    flex-direction: column
`;

const SearchBar = styled.div`

`;


export default Neighbor;