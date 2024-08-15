
export function getTasks() {
    return fetch('/api/tasks')
        .then(data => data.json());
}

export function getTask(id) {
    return fetch(`/api/${id}`)
        .then(data => data.json());
}

export function updateTask(id, data) {
    const options = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    };

    return fetch(`/api/${id}`, options)
        .then(data => data.json());
}

export function createTask(data) {
    const options = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    }

    return fetch(`/api/newTask`, options)
        .then(data => data.json());
}
//
//export function uploadFile(file) {
//    const formData = new FormData();
//    formData.append('file', file);
//
//    const options = {
//        method: 'POST',
//        body: formData
//    };
//
//    return fetch('/uploadFile', options)
//        .then(response => {
//            if (!response.ok) {
//                throw new Error('File upload failed');
//            }
//            return response.json();
//        });
//}
