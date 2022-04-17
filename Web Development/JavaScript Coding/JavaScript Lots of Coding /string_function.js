const capitalizeString = str => str.toUpperCase();

export{capitalizeString};
export const foo = "bar";
export const bar = "foo";

//export defaults are fallback exports which are used when you only want to export one thing from file
export default function subtract(x,y){
 return x-y;
}