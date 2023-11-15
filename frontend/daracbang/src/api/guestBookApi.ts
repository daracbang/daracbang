import { AxiosError, AxiosResponse, isAxiosError } from "axios";
import { backApiInstance, jwtApiInstance } from "./http";
import { ResponseDataType } from "./responseType";
import { MemberInfo } from "../store/memberReducer";
import { getToken } from "../utils/tokenUtil";


const http = backApiInstance();
const jwtHttp = jwtApiInstance();

export const getGuestBook = async (ownerId: number): Promise<AxiosResponse<GuestBookObject>> => {
    const GET_GUESTBOOK = "/api/guestbooks/"+ownerId;
    const token = getToken();
    return await http.get(GET_GUESTBOOK, {
        headers: {
            'Authorization': `Bearer ${token}` 
        }
    });
  };

export class GuestBookItem {
    gusetBookId: number;
    nickname: string;
    writerId: number;
    profileImage: string;
    content: string;
    createdAt: string;

    constructor(gusetBookId: number, nickname: string, writerId: number, profileImage: string, content: string, createdAt: string) {
        this.gusetBookId = gusetBookId;
        this.nickname = nickname;
        this.writerId = writerId;
        this.profileImage = profileImage;
        this.content = content;
        this.createdAt = createdAt;
    }
}

export class GuestBookObject {
    message: string;
    lastId: number;
    datas: GuestBookItem[];

    constructor(message: string, lastId: number, datas: GuestBookItem[]) {
        this.message = message;
        this.lastId = lastId;
        this.datas = datas;
    }
}
