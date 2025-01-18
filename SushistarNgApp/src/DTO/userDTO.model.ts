export interface UserDTO {
	email: string;
	username: string;
	password: string;
	roleId: number;
}

export enum UserRoleEnum {
	customer = 1,
	admin
}