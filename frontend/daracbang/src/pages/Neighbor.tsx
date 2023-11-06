import React from 'react';
import Head from '../components/Head';
import styled from "@emotion/styled";
import Dial from '../components/SpeedDial';
import { Button, Card, TextField, ThemeProvider, Typography, createTheme } from '@mui/material';
import Search from '../assets/images/search.png';
import SearchNeigh from '../components/SearchNeigh';
import AddMusic from '../components/AddMusic';


const Neighbor = () => {

    const theme = createTheme({
        typography: {
            fontFamily: "KyoboHand"
        },
    });

    return (
        <div>
            <Head />
            <ContainerWrap style={{ backgroundColor: "#F2EBEB" }}>
                <ContentWrap>
                    <Card style={{ height: "600px", width: "420px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "100px", boxShadow: "3px 3px 2px 1px #bdbdbd", borderRadius: "15px" }} >
                        <ThemeProvider theme={theme} >
                            <TextField variant="standard" style={{ width: "300px", marginLeft: "20px", marginRight: "10px", marginTop: "15px", fontFamily: "KyoboHand" }} />
                        </ThemeProvider>
                        <Button>
                            <img src={Search} alt='search' style={{ marginTop: "15px" }} />
                        </Button>
                        <SearchNeigh />
                        <Card style={{ height: "400px", width: "400px", margin: "10px", marginTop: "30px", boxShadow: "3px 3px 2px 1px #bdbdbd", borderRadius: "15px" }} >
                            <SearchNeigh />
                        </Card>
                    </Card>

                    <CardWrap>

                        <Card style={{ height: "150px", marginLeft: "50px", marginBottom: "10px", boxShadow: "3px 3px 2px 1px #bdbdbd", borderRadius: "15px", display: "flex", flexDirection: "column" }} >
                            <ThemeProvider theme={theme} >
                                <TextField multiline style={{ fontFamily: "KyoboHand", marginTop: "15px", marginLeft: "30px", width: "360px" }} rows={2}></TextField>
                            </ThemeProvider>
                            <Button variant='outlined' style={{ marginLeft: "300px", marginTop: "10px", width: "90px", height: "30px" }}>수정하기</Button>
                        </Card>
                        <Card style={{ height: "440px", width: "420px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "50px", boxShadow: "3px 3px 2px 1px #bdbdbd", borderRadius: "15px" }} >
                            <Typography style={{ fontFamily: "omyu_pretty", textAlign: "center", fontWeight: "bold", fontSize: "20px", marginTop: "5px" }}>나의 BFF</Typography>
                            <SearchNeigh />
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