import { combineReducers } from "redux";
import { memberReducer } from "./memberReducer";
import storage from "redux-persist/lib/storage/session";
import { persistReducer } from "redux-persist";
import {BGMReducer} from "./bgmReducer";

const rootRedcuer = combineReducers({ memberReducer,BGMReducer});
const persistConfig: any = {
  key: "root",
  storage: storage,
  whitelist: ["memberReducer"],
};

export default persistReducer(persistConfig, rootRedcuer);
export type RootState = ReturnType<typeof rootRedcuer>;
