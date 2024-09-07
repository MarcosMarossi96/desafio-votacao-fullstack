import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event';
import Input from './index'
import '@testing-library/jest-dom';

describe('<Input />', () => {
    const id = "test-input-id"

    it('should render', () => {
        render(<Input label="Test" name={id} />)
        const labelElement = screen.getByLabelText('Test')

        expect(labelElement).toBeInTheDocument()
        expect(labelElement).toHaveAttribute('type', 'text')

        const inputElement = screen.getByTestId(id)
        expect(inputElement).toBeInTheDocument()
    })

    it('should only have numbers', async () => {
        render(
            <Input
                label="cpf"
                name={id}
                type="number"
                minLength={11}
                maxLength={11}
            />,
        )

        const numberElement = screen.getByTestId(id)

        expect(numberElement).toBeInTheDocument()
        expect(numberElement).toHaveAttribute('type', 'number')

        await userEvent.type(numberElement, '184844884840000000')
        // minLength = 11, maxLength = 11, value have type number
        expect(numberElement).toHaveValue(18484488484)
    })
})