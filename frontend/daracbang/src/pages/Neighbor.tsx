import React from 'react';
import Head from '../components/Head';
import styled from "@emotion/styled";
import Dial from '../components/SpeedDial';
import { Button, Card, TextField, Typography, createTheme } from '@mui/material';

import Delete from '../assets/images/delete.png';
import Search from '../assets/images/search.png';
import SearchNeigh from '../components/SearchNeigh';


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
                    <Card style={{ height: "600px", width: "420px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "100px" }} >
                        <TextField id="standard-basic" variant="standard" style={{ width: "300px", marginLeft: "20px", marginRight: "10px", marginTop: "15px" }} />
                        <Button>
                            <img src={Search} alt='search' style={{ marginTop: "15px" }} />
                        </Button>
                        <Card style={{ height: "400px", width: "400px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", margin: "10px", marginTop: "110px" }} >
                            <SearchNeigh />
                        </Card>
                    </Card>

                    <CardWrap>

                        <Card style={{ height: "150px", width: "420px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "50px", marginBottom: "10px" }} >

                        </Card>
                        <Card style={{ height: "440px", width: "420px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "50px" }} >

                        </Card>
                    </CardWrap>

                    <Card style={{ height: "300px", width: "420px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "50px" }} >

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

const NeighInfo = styled.div`
display: flex;
flex-direction: column
`;

export default Neighbor;