import React from 'react';
import Head from '../components/Head';
import styled from "@emotion/styled";
import MyDarac from "../assets/images/room2.png";
import Dial from '../components/SpeedDial';
import { Button, FormControlLabel, Paper, Radio, RadioGroup, TextField, Typography } from '@mui/material';
import { Link } from 'react-router-dom';


const Guestbook = () => {

    return (
        <div>
            <Head />
            <ContainerWrap style={{ backgroundColor: "#F2EBEB" }}>
                <img src={MyDarac} alt='myDarac' style={{ marginLeft: "50px", height: "300px", marginTop: "260px" }} />

                <ContentWrap>
                    <Paper style={{ height: "530px", borderRadius: "10px", boxShadow: "3px 3px 5px 1px #bdbdbd" }}>
                        <Typography style={{ marginLeft: "50px", paddingTop: "30px", fontFamily: "omyu_pretty", fontWeight: "bold", fontSize: "30px" }}>공개범위</Typography>
                        <RadioGroup
                            row
                            aria-labelledby="demo-row-radio-buttons-group-label"
                            name="row-radio-buttons-group"
                            style={{ display: "flex", justifyContent: "center", marginTop: "15px" }}
                        >
                            <FormControlLabel value="female" control={<Radio />} label="전체공개" style={{ marginRight: "80px" }} sx={{ '.MuiFormControlLabel-label': { fontFamily: "omyu_pretty", fontSize: "20px" } }} />
                            <FormControlLabel value="male" control={<Radio />} label="친구만공개" style={{ marginRight: "80px" }} sx={{ '.MuiFormControlLabel-label': { fontFamily: "omyu_pretty", fontSize: "20px" } }} />
                            <FormControlLabel value="other" control={<Radio />} label="나만보기" sx={{ '.MuiFormControlLabel-label': { fontFamily: "omyu_pretty", fontSize: "20px" } }} />
                        </RadioGroup>

                        <TextField variant="outlined" multiline
                            rows={10} inputProps={{ maxLength: 1000 }} style={{ width: "600px", height: "100px", marginLeft: "50px", marginTop: "30px" }}>

                            일기작성

                        </TextField>
                        <Typography style={{ marginTop: "10px", marginLeft: "55px" }} sx={{ fontFamily: "omyu_pretty", color: "#B22727" }}>* 감정분석을 위해 최소 50자 이상 작성해야 등록이 가능합니다</Typography>



                        <Link to="/diary">
                            <Button variant='outlined' style={{ left: "570px", marginTop: "10px", fontFamily: "omyu_pretty" }}>작성완료</Button>
                        </Link>

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
    padding-bottom: 66px;
`;

const Navi = styled.div`

`;

const ContentWrap = styled.div`
    padding-top: 20px;
    margin-left: 150px;
    width:  700px;
    
`;

export default Guestbook;