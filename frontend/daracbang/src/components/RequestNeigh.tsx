import { Button, Card, Typography } from '@mui/material';
import * as React from 'react';
import Ban from '../assets/images/ban.png';
import Plus from '../assets/images/plus.png';
import styled from "@emotion/styled";
import { MemberInfo } from '../store/memberReducer';


interface RequestNeighProps {
    requests: MemberInfo;
}

const RequestNeigh: React.FC<RequestNeighProps> = ({ requests }) => {

    console.log(requests.nickname);
    return (
        < Card style={{ height: "70px", width: "360px", margin: "15px auto", boxShadow: "2px 2px 1px 1px #eeeeee", borderRadius: "10px" }}>
            <NeighInfo style={{ display: "flex", flexDirection: "row", marginTop: "10px", marginLeft: "10px" }}>
                <img src={requests.profileImage} alt='sun' style={{ height: "20px", marginLeft: "10px" }} />
                <Typography style={{ fontFamily: "omyu_pretty", fontWeight: "bold", width: "100px", marginLeft: "10px", marginRight: "100px" }}>{requests.nickname}</Typography>
                <Button style={{ marginLeft: "30px", padding: 0 }}><img src={Plus} alt='plus' style={{ height: "20px" }} /></Button>
                <Button style={{ padding: 0 }}><img src={Ban} alt='ban' style={{ height: "20px" }} /></Button>
            </NeighInfo>
            <Typography style={{ fontFamily: 'omyu_pretty', marginLeft: "20px" }}>{requests.introduce}</Typography>
        </ Card>


    );
}

const NeighInfo = styled.div`

`;

export default RequestNeigh;