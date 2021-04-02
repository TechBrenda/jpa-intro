import React from 'react';
import {Link} from 'react-router-dom';

const Home = () => (
    <div className={'jumbotron'}>
        <h1>JPA Demo</h1>
        <p>Model, Repository, Controller to keep SQL out of your app code.</p>
        <Link to={'about'} className={'btn btn-primary btn-lg'}>
            Learn more
        </Link>
    </div>
);

export default Home;