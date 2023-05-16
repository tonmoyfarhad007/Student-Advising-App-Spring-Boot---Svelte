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

function getStudentDataFromSession() {
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        setStudentDataToStoreFromSession:  ()=>{
            let name  = sessionStorage.getItem('name');
            let email = sessionStorage.getItem('email');
            let phoneNo = sessionStorage.getItem('phoneNo');
            let studentId = sessionStorage.getItem('studentId');
            let active = sessionStorage.getItem('active');
            let departmentName = sessionStorage.getItem('departmentName');
            
            let jsonData = {
                name: name,
                email: email,
                phoneNo: phoneNo,
                studentId: studentId,
                active: active,
                departmentName: departmentName
            }
            studentProfileData.setStudentData(jsonData);
        }    
        
    }
}


export const studentProfileData = storeStudentData();
export const studentDataFromSession = getStudentDataFromSession();
export let isStudentLoggedIn    = writable(false );
export let showUpdateComponent  = writable(false);
export let showResetComponent   = writable(false);