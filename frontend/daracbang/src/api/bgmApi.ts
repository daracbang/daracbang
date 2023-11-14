import { AxiosError, AxiosResponse, isAxiosError } from "axios";
import { backApiInstance, jwtApiInstance } from "./http";
import { ResponseDataType } from "./responseType";
import { MyBgm } from "../store/bgmReducer";

const http = backApiInstance();

export const getBgmList = async (
  memberId: number
): Promise<AxiosResponse<{ bgms: MyBgm[] }>> => {
  const GET_BGM_URL = "/api/bgms";

  return await http.get(`${GET_BGM_URL}/${memberId}`);
};
