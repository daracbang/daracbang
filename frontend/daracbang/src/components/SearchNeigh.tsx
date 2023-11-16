import { Button, Card, Typography } from '@mui/material';
import * as React from 'react';
import Ban from '../assets/images/ban.png';
import Plus from '../assets/images/plus.png';
import styled from "@emotion/styled";
import { MemberInfo } from '../store/memberReducer';
import axios from 'axios';
import { getToken } from '../utils/tokenUtil';

interface SearchNeighProps {
    searchs: String | null;
}

const SearchNeigh: React.FC<SearchNeighProps> = ({ searchs }) => {

    const [searchMember, setSearchMember] = React.useState<MemberInfo | null>(null);
    const accessToken = getToken();

    axios.get(`/api/neighbors/search`, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
        params: { nickname: searchs },
    })
        .then((response) => {
            console.log(response.data);
            setSearchMember(response.data); // API 호출 완료 후에 studyrooms 업데이트
            console.log(searchMember);
        })
        .catch((error) => {
            // 오류 처리
            console.log(error);
        });

    return (
        <Card style={{ height: "70px", width: "360px", margin: "15px auto", boxShadow: "2px 2px 1px 1px #eeeeee", borderRadius: "10px" }} >
            <NeighInfo style={{ display: "flex", flexDirection: "row", marginTop: "10px", marginLeft: "10px" }}>
                <img src={searchMember?.profileImage} alt='sun' style={{ height: "20px", marginLeft: "10px" }} />
                <Typography style={{ fontFamily: "omyu_pretty", fontWeight: "bold", width: "100px", marginLeft: "10px", marginRight: "100px" }}>{searchMember?.nickname}</Typography>
                <Button style={{ marginLeft: "30px", padding: 0 }}><img src={Plus} alt='plus' style={{ height: "20px" }} /></Button>
                <Button style={{ padding: 0 }}><img src={Ban} alt='ban' style={{ height: "20px" }} /></Button>
            </NeighInfo>
            <Typography style={{ fontFamily: 'omyu_pretty', marginLeft: "20px" }}>{searchMember?.introduce}</Typography>
        </ Card>

    );
}

const NeighInfo = styled.div`

`;

export default SearchNeigh;