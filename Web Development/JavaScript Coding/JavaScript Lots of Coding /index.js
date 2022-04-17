//Difference between import and export
import { capitalizeString } from "./string_function"
const cap = capitalizeString("hello");
console.log(cap);

//use exports to reuse code blocks that be used elsewhere

//importants everything
import * as capitalizeString from "capitalize_string";

//import default export
import subtract from "string_function"
console.log(subtract(7,4));