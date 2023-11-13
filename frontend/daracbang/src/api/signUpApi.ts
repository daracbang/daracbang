import { AxiosError, isAxiosError } from "axios";
import { backApiInstance } from "./http";
import { ResponseDataType } from "./responseType";

const http = backApiInstance();

export const checkDuplicateLoginId = async (loginId: string): Promise<void> => {
  const CHECK_LOGINID_URL = "/api/members/login-id";

  await http.get(`${CHECK_LOGINID_URL}/${loginId}`);
};

export const checkDuplicatedNickname = async (nickname: string): Promise<void> => {
  const CHECK_NICKNAME_URL = "/api/members/nickname";
  await http.get(`${CHECK_NICKNAME_URL}/${nickname}`);
};

export const signUp = async (formdata: FormData) => {
  const SIGNUP_URL = "/api/members";
  await http.post(SIGNUP_URL, formdata, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};
