import React, { useState } from 'react';

const UploadFiles2 = () => {
    const [file, setFile] = useState(null);
    const [message, setMessage] = useState('');
    const [downloadUri, setDownloadUri] = useState('');

    const onFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const onFileUpload = () => {
        if (!file) {
            setMessage('Please select a file to upload.');
            return;
        }

        const formData = new FormData();
        formData.append('file', file);

        fetch('/pemTopk8', {
            method: 'POST',
            body: formData,
            headers: {
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('File upload failed choose correct file or file size is to large');
            }
            return response.json();
        })
        .then(data => {
            setMessage(`File uploaded successfully: ${data.fileName}`);
            setDownloadUri(data.downloadUri);
        })
        .catch(error => {
            setMessage('File upload failed.');
            console.error('There was an error uploading the file!', error);
        });
    };

    return (
        <div>
            <h2>File Upload</h2>
            <input type="file" onChange={onFileChange} />
            <button onClick={onFileUpload}>Upload</button>

            {message && <p>{message}</p>}
            {downloadUri && (
                <p>
                    Download file: <a href={downloadUri}>{downloadUri}</a>
                </p>
            )}
        </div>
    );
};

export default UploadFiles2;
