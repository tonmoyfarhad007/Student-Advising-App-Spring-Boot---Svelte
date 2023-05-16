import { writable } from 'svelte/store';

function storeTeacherData(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        setSingleTeacherData:  (data)=>{
            console.log(data);
            set(data);
        }    
        
    }
}


function getTeacherDataFromSession() {
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        setTeacherDataToStoreFromSession:  ()=>{
            let name  = sessionStorage.getItem('name');
            let email = sessionStorage.getItem('email');
            let phoneNo = sessionStorage.getItem('phoneNo');
            let teacherId = sessionStorage.getItem('teacherId');
            let active = sessionStorage.getItem('active');
            let departmentName = sessionStorage.getItem('departmentName');
            
            let jsonData = {
                name: name,
                email: email,
                phoneNo: phoneNo,
                teacherId: teacherId,
                active: active,
                departmentName: departmentName
            }
            singleTeacherData.setSingleTeacherData(jsonData);
        }    
        
    }
}


export const singleTeacherData = storeTeacherData();
export const teacherDataFromSession = getTeacherDataFromSession();
export let isTeacherLoggedIn   = writable(false);