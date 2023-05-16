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

function teacherWiseStudentData(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        getTeacherWiseStudentData: async (email)=>{
            console.log(email);
            const jwtToken = sessionStorage.getItem('jwtToken');
            let url ='http://localhost:8080/getTeacherWiseAllStudents?teacherEmail='+email;

            let response = await fetch(url, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${jwtToken}`,
                },
            });
            const data = await response.json();
            console.log(data);
            set(data);
            
        },
        refesh: ()=>{
            set([]);
        }


        
    }
}

function advisingRequestData(){
    
    const { subscribe, set, update } = writable("");

    return {
        subscribe,
        set: (val) => {set(val);},

        print: (msg) => {console.log(msg)},

        getRequestData: async (email)=>{
            console.log(email);
            const jwtToken = sessionStorage.getItem('jwtToken');
            let url ='http://localhost:8080/getTeacerWiseRequestList?teacherEmail='+email;

            let response = await fetch(url, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${jwtToken}`,
                },
            });
            const data = await response.json();
            console.log(data);
            let arr = [];
            data.forEach(e => {
                if(e.status=="pending"){
                    arr.push(e);
                }
            });
            set(arr);
            
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


export const singleTeacherData      = storeTeacherData();
export const requestList            = advisingRequestData();
export const teacherDataFromSession = getTeacherDataFromSession();
export const teacherWiseStudentList = teacherWiseStudentData();
export let isTeacherLoggedIn   = writable(false);