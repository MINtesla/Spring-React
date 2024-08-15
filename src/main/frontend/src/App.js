import 'bootstrap/dist/css/bootstrap.min.css';
import {Routes, Route} from 'react-router-dom';
import Home from './Home';
import Navbar from './Navbar';
import Task from './Task';
import UploadFiles1 from './UploadFiles1';
import UploadFiles2 from './UploadFiles2';
import FileDownload from './FileDownload';

const App = () => {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/newurl1" element={<UploadFiles1 />} />
        <Route path="/newurl2" element={<UploadFiles2 />} />
        <Route path="/downloadFile" element={<FileDownload />} />
        <Route path="/downloadFile/:filename" element={<FileDownload />} />
        <Route path="/api/:id" element={<Task />} />
      </Routes>
    </>
  );
}

export default App;