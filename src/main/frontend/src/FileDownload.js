//
//
//import React, { useState } from 'react';
//
//const FileDownload = () => {
//    const [fileCode, setFileCode] = useState('');
//    const [message, setMessage] = useState('');
//
//    const onFileCodeChange = (e) => {
//        setFileCode(e.target.value);
//    };
//
//    const onFileDownload = () => {
//        if (!fileCode) {
//            setMessage('Please enter a file code.');
//            return;
//        }
//
//        // Construct the download URL using the file code
//        const downloadUrl = `/downloadFile/${fileCode}`;
//
//        fetch(downloadUrl, {
//            method: 'GET',
//        })
//        .then(response => {
//            if (!response.ok) {
//                if (response.status === 404) {
//                    throw new Error('File not found');
//                }
//                throw new Error('File download failed');
//            }
//            return response.blob();
//        })
//        .then(blob => {
//            const url = window.URL.createObjectURL(blob);
//            const a = document.createElement('a');
//            a.href = url;
//            a.download = fileCode; // You can customize the file name here
//            document.body.appendChild(a);
//            a.click();
//            a.remove();
//            window.URL.revokeObjectURL(url);
//            setMessage('File downloaded successfully.');
//        })
//        .catch(error => {
//            setMessage(error.message);
//            console.error('There was an error downloading the file!', error);
//        });
//    };
//
//    return (
//        <div>
//            <h2>File Download</h2>
//            <input
//                type="text"
//                placeholder="Enter file code"
//                value={fileCode}
//                onChange={onFileCodeChange}
//            />
//            <button onClick={onFileDownload}>Download</button>
//
//            {message && <p>{message}</p>}
//        </div>
//    );
//};
//
//export default FileDownload;
//

import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

const FileDownload = () => {
    const { filename } = useParams(); // Get the filename from the URL
    const [fileCode, setFileCode] = useState(filename || ''); // Initialize with filename if available
    const [message, setMessage] = useState('');

    useEffect(() => {
        if (filename) {
            onFileDownload();
        }
    }, [filename]);

    const onFileCodeChange = (e) => {
        setFileCode(e.target.value);
    };

    const onFileDownload = () => {
        if (!fileCode) {
            setMessage('Please enter a file code.');
            return;
        }

        const downloadUrl = `/downloadFile/${fileCode}`;

        fetch(downloadUrl, {
            method: 'GET',
        })
        .then(response => {
            if (!response.ok) {
                if (response.status === 404) {
                    throw new Error('File not found');
                }
                throw new Error('File download failed');
            }
            return response.blob();
        })
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = fileCode; // Customize the file name here if needed
            document.body.appendChild(a);
            a.click();
            a.remove();
            window.URL.revokeObjectURL(url);
            setMessage('File downloaded successfully.');
        })
        .catch(error => {
            setMessage(error.message);
            console.error('There was an error downloading the file!', error);
        });
    };

    return (
        <div>
            <h2>File Download</h2>
            <input
                type="text"
                placeholder="Enter file code"
                value={fileCode}
                onChange={onFileCodeChange}
            />
            <button onClick={onFileDownload}>Download</button>

            {message && <p>{message}</p>}
        </div>
    );
};

export default FileDownload;
