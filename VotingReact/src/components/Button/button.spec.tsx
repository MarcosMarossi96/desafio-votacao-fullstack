import { render, screen } from '@testing-library/react'
import Button from './index'
import '@testing-library/jest-dom';

describe('<Button />', () => {
    beforeAll(() => {
        const style = document.createElement('style');
        style.innerHTML = `
          :root {
            --primary-color: blue;
            --disable-color: gray;
          }
        `;
        document.head.appendChild(style);
    });

    it('should have a label', () => {
        render(<Button label="Test" />)
        const spanElement = screen.getByText('Test')

        expect(spanElement).toBeInTheDocument()
        expect(spanElement.tagName).toBe('SPAN');
    })

    it('should primary color', () => {
        render(<Button label="Test" />)
        const buttonElement = screen.getByRole('button', { name: 'Test' })

        expect(buttonElement).toBeInTheDocument()
        expect(buttonElement).toHaveStyle({ backgroundColor: 'var(--primary-color)' })

        const btnStyle = window.getComputedStyle(buttonElement);
        expect(btnStyle.backgroundColor).toBe('blue');
    })

    it('should disable color', () => {
        render(<Button label="Test" disabled={true} />)
        const buttonElement = screen.getByRole('button', { name: 'Test' })

        expect(buttonElement).toBeInTheDocument()
        expect(buttonElement).toHaveStyle({ backgroundColor: 'var(--disable-color)' })
    })
})