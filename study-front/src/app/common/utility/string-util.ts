
export class StringUtil {

    public static isEmpty(val: any): boolean {
        if (val === '' || val === null || val === undefined) {
            return true;
        } else {
            return false;
        }
    }
}