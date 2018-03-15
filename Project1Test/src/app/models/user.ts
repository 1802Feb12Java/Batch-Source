export class User {
    'id': number;
    'roleId': number;
    
    constructor (id: number, roleID: number){
        this.id = id;
        this.roleId = roleID;
    }

    getId(): number{
        return this.id;
    }

    getRoleId(): number{
        return this.roleId;
    }
}
