import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-dark bg-black">
                <div className="container-fluid">
                    <a href="#" className="navbar-brand">Task App</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <Link to={`/`} className="nav-link">Home</Link>
                            </li>
                            <li className="nav-item">
                                <Link to={`/api/newTask`} className="nav-link">NewTask</Link>
                            </li>
                            <li className="nav-item">
                                <Link to={`/NEWURL1`} className="nav-link">PEM->DER</Link>
                            </li>
                            <li className="nav-item">
                                <Link to={`/NEWURL2`} className="nav-link">PEM->PK8</Link>
                            </li>
                            <li className="nav-item">
                            <Link to={`/downloadFile`} className="nav-link">DownloadFiles</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </>
    );
}

export default Navbar;