export class ApiResponse {
    private static OK = 200;
    private static ERROR = 1;
    private static WARNING = 2;
    status: number;
    msg: any;
    data: any;

    public static isOK(date: ApiResponse): boolean {
        if (date.status === this.OK) {
            console.log('Response Ok');
            return true;
        }
        console.log('Response Ng');
        return false;
    }

    public static isWarning(date: ApiResponse): boolean {
        return (date.status === this.WARNING);
    }

    public static isError(date: ApiResponse): boolean {
        return (date.status === this.ERROR);
    }
}