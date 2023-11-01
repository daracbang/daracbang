import React from 'react';
import Head from '../components/Head';
import styled from "@emotion/styled";
import MyDarac from "../assets/images/room2.png";
import Dial from '../components/SpeedDial';
import { Button, Card, CardContent, FormControlLabel, Paper, Radio, RadioGroup, TextField, Typography } from '@mui/material';

const Diary: React.FC = () => {

    return (
        <div>
            <Head />
            <ContainerWrap style={{ backgroundColor: "#F2EBEB" }}>
                <img src={MyDarac} alt='myDarac' style={{ marginLeft: "40px", height: "300px", marginTop: "280px" }} />

                <ContentWrap>
                    <Paper style={{ height: "550px" }}>
                        <Typography style={{ marginLeft: "50px", paddingTop: "30px" }}>공개범위</Typography>
                        <RadioGroup
                            row
                            aria-labelledby="demo-row-radio-buttons-group-label"
                            name="row-radio-buttons-group"
                            style={{ display: "flex", justifyContent: "center" }}
                        >
                            <FormControlLabel value="female" control={<Radio />} label="전체공개" />
                            <FormControlLabel value="male" control={<Radio />} label="친구만공개" />
                            <FormControlLabel value="other" control={<Radio />} label="나만보기" />
                        </RadioGroup>
                        <TextField variant="outlined" multiline
                            maxRows={10} inputProps={{ maxLength: 1000 }} style={{ width: "600px", height: "100px", marginLeft: "50px", marginTop: "100px" }}>
                            일기작성
                        </TextField>
                        <Button style={{ left: "550px", marginTop: "200px" }}>작성완료</Button>
                    </Paper>
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
    padding-top: 40px;
    padding-bottom: 47px;
`;

const Navi = styled.div`

`;

const ContentWrap = styled.div`
    padding-top: 20px;
    margin-left: 150px;
    width:  700px;
`;

export default Diary;