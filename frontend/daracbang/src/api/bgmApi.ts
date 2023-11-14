import { AxiosError, AxiosResponse, isAxiosError } from "axios";
import { backApiInstance, jwtApiInstance } from "./http";
import { ResponseDataType } from "./responseType";
import { MyBgm } from "../store/bgmReducer";

const jwtHttp = jwtApiInstance();

export const getBgmList = async (
  memberId: number
): Promise<AxiosResponse<{ bgms: MyBgm[] }>> => {
  const GET_BGM_GET_URL = "/api/bgms";

  return await jwtHttp.get(`${GET_BGM_GET_URL}/${memberId}`);
};

export const saveBgm = async (
  memberId: number,
  bgmName: string,
  url: string
): Promise<void> => {
  const POST_BGM_REGISTER_URL = "/api/bgms";
  jwtHttp.post(`${POST_BGM_REGISTER_URL}/${memberId}`, { bgmName, url });
};
