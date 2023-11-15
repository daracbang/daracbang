import React from 'react';
import Head from '../components/Head';
import styled from "@emotion/styled";
import MyDarac from "../assets/images/room2.png";
import MoodTracker from '../components/MoodTracker';
import { Button, Card, Dialog, DialogActions, DialogContent, TextField, ThemeProvider, Typography, createTheme } from '@mui/material';
import Dial from '../components/SpeedDial';
import DayDiary from '../components/DayDiary';
import Comment from '../components/Comment';
import Foot from '../assets/images/foot.png';
import FootPrint from '../assets/images/footprint.png';


const Diary = () => {
    const [open, setOpen] = React.useState(false);
    
    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const theme = createTheme({
        typography: {
            fontFamily: "KyoboHand"
        },
    });


    return (
        <div>
            <Head />
            <ContainerWrap style={{ backgroundColor: "#F2EBEB" }}>
                <LsideWrap>
                    <MoodTracker />
                    <img src={MyDarac} alt='myDarac' style={{ height: "300px", marginTop: "10px" }} />
                </LsideWrap>


                <ContentWrap>
                    <DiaryWrap>
                        <Card style={{ height: "400px", width: "700px", marginLeft: "100px", borderRadius: "10px", boxShadow: "3px 3px 5px 1px #bdbdbd" }}>
                            <DayDiary />
                        </Card>


                        <Card style={{ height: "200px", width: "700px", backgroundColor: "rgba( 255, 255, 255, 0.3 )", marginLeft: "100px", marginTop: "10px" }} >
                            <Comment />
                            <Comment />
                            <Comment />
                        </Card>

                    </DiaryWrap>


                    <Button variant="outlined" onClick={handleClickOpen} style={{ borderColor: "#F2EBEB", height: "40px", width: "170px", color: "black", marginLeft: "10px" }}>
                        <img src={Foot} alt='foot' style={{ marginRight: "10px" }} />
                        발자국 남기기
                    </Button>
                    <Dialog open={open} onClose={handleClose}>
                        <DialogContent style={{ height: "350px", marginTop: "20px" }}>
                            <ThemeProvider theme={theme}>
                                <TextField variant="outlined" multiline
                                    rows={10} inputProps={{ maxLength: 1000 }} style={{ width: "530px", height: "100px", marginLeft: "10px", marginTop: "30px", fontFamily: "KyoboHand" }}>
                                </TextField>
                            </ThemeProvider>
                            <Typography style={{ fontFamily: "KyoboHand", fontWeight: "bold", color: "#B22727", marginLeft: "20px", marginTop: "170px" }}>20자 이하로 작성 시 감정분석이 되지 않습니다. 작성을 완료했다면 발자국을 눌러주세요!</Typography>
                        </DialogContent>
                        <DialogActions style={{ marginBottom: "10px" }}>
                            <Button onClick={handleClose}><img src={FootPrint} alt='foot' /></Button>
                        </DialogActions>
                    </Dialog>
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
    padding-top: 20px;
    padding-bottom: 37px;
`;

const LsideWrap = styled.div`
    display: flex;
    flex-direction: column;
    margin-left: 80px;
`;

const ContentWrap = styled.div`
    display: flex;
    flexDirection: row;
`;

const Navi = styled.div`

`;

const DiaryWrap = styled.div`

`;



export default Diary;