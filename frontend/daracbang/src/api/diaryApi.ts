import { AxiosResponse } from "axios";
import { jwtApiInstance } from "./http";

const jwtHttp = jwtApiInstance();

export const uploadDiary = async (scope: string, content: string) => {
  const URL = "/api/diaries";
  await jwtHttp.post(URL, { scope, content });
};
