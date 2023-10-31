import React from 'react';
import Head from '../components/Head';
import styled from "@emotion/styled";
import MyDarac from "../assets/images/room2.png";

const Diary = () => {

    return (
        <div>
            <Head />
            <ContainerWrap style={{ backgroundColor: "#F2EBEB" }}>
                <img src={MyDarac} alt='myDarac' />
            </ContainerWrap>
        </div>

    );
};

const ContainerWrap = styled.div`
`;

export default Diary;