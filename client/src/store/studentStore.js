import { writable } from 'svelte/store';

function storeStudentData(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        setStudentData:  (data)=>{
            console.log(data);
            set(data);
        }    
        
    }
}


export const studentProfileData = storeStudentData();