import { Button, Card, Typography } from '@mui/material';
import * as React from 'react';
import Ban from '../assets/images/ban.png';
import Plus from '../assets/images/plus.png';
import styled from "@emotion/styled";
import { NeighborObject } from '../api/neighborApi';
import axios from 'axios';
import { getToken } from '../utils/tokenUtil';

interface SearchNeighProps {
    data: NeighborObject | null;
}

const SearchNeigh: React.FC<SearchNeighProps> = ({ data }) => {
    const accessToken = getToken();

    console.log("--" + data?.nickname);


    const handleAccept = async (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
        event.preventDefault();

        axios.put(`/api/neighbors/accepts/` + data?.memberId, {

            headers: {
                Authorization: `Bearer ${accessToken}`,
            },

        })
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                // 오류 처리
                console.log(error);
            });
    }


    const handleDelete = async (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
        event.preventDefault();

        axios.delete(`/api/neighbors/` + data?.memberId, {

            headers: {
                Authorization: `Bearer ${accessToken}`,
            },
        })
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                // 오류 처리
                console.log(error);
            });
    }

    return (
        <Card style={{ height: "70px", width: "360px", margin: "15px auto", boxShadow: "2px 2px 1px 1px #eeeeee", borderRadius: "10px" }} >
            <NeighInfo style={{ display: "flex", flexDirection: "row", marginTop: "10px", marginLeft: "10px" }}>
                <img src={data?.profileImage[1]} alt='sun' style={{ height: "20px", marginLeft: "10px" }} />
                <Typography style={{ fontFamily: "omyu_pretty", fontWeight: "bold", width: "100px", marginLeft: "10px", marginRight: "100px" }}>{data?.nickname}</Typography>
                <Button onClick={handleAccept} style={{ marginLeft: "30px", padding: 0 }}><img src={Plus} alt='plus' style={{ height: "20px" }} /></Button>
                <Button onClick={handleDelete} style={{ padding: 0 }}><img src={Ban} alt='ban' style={{ height: "20px" }} /></Button>
            </NeighInfo>
        </ Card>

    );
}

const NeighInfo = styled.div`

`;

export default SearchNeigh;