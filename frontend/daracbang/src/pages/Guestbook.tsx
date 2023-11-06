import React from 'react';
import Head from '../components/Head';
import styled from "@emotion/styled";
import MyDarac from "../assets/images/room2.png";
import Dial from '../components/SpeedDial';
import NeighPost from '../components/NeighPost';
import { Button, Card, TextField, ThemeProvider, Typography, createTheme } from '@mui/material';
import Sun from '../assets/images/sun.png';


const Guestbook = () => {

    const theme = createTheme({
        typography: {
            fontFamily: "KyoboHand"
        },
    });

    return (
        <div>
            <Head />
            <ContainerWrap style={{ backgroundColor: "#F2EBEB" }}>
                <img src={MyDarac} alt='myDarac' style={{ marginLeft: "80px", height: "300px", marginTop: "300px" }} />

                <ContentWrap>
                    <Card style={{ height: "480px", width: "700px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "130px" }} >
                        <NeighPost />
                        <NeighPost />
                        <NeighPost />
                    </Card>

                    <Card style={{ height: "135px", width: "700px", marginLeft: "130px", marginTop: "10px" }}>
                        <NeighInfo style={{ display: "flex", flexDirection: "row", marginTop: "10px" }}>
                            <img src={Sun} alt='sun' style={{ height: "20px", marginLeft: "10px" }} />
                            <Typography style={{ fontFamily: "omyu_pretty", fontWeight: "bold", marginLeft: "10px" }}>김싸피</Typography>
                            <Typography style={{ height: "20px", marginLeft: "550px", fontFamily: "omyu_pretty", fontWeight: "bold" }} >2023.11.03</Typography>
                        </NeighInfo>
                        <ThemeProvider theme={theme}>
                            <TextField style={{ fontFamily: "KyoboHand", width: "650px", marginLeft: "25px", marginTop: "10px" }}>안녕~!</TextField>
                        </ThemeProvider>

                        <Button variant='outlined' style={{ fontFamily: "omyu_pretty", marginLeft: "580px", marginTop: "5px", height: "25px" }}>방명록남기기</Button>
                    </Card>

                </ContentWrap>
                <Navi style={{ transform: 'translateZ(0px)', flexGrow: 1 }}>
                    <Dial />
                </Navi>
            </ContainerWrap>
        </div>
    );
};

const ContainerWrap = styled.div`
    display: flex;
    flex-direction: row;
    padding-top: 20px;
    padding-bottom: 22px;
`;

const ContentWrap = styled.div`
    display: flex;
    flex-direction: column;
`;

const Navi = styled.div`

`;

const NeighInfo = styled.div`

`;


export default Guestbook;