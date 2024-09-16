import * as React from 'react'
import * as ReactDOM from 'react-dom/client'
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import './index.css';

const rootElement = document.getElementById('root');
if (rootElement === null) {
  throw new Error();
}

ReactDOM.createRoot(rootElement).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>,
)