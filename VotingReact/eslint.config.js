import globals from "globals";
import pluginJs from "@eslint/js";
import tseslint from "typescript-eslint";
import pluginReactConfig from "eslint-plugin-react/configs/recommended.js";
import { fixupConfigRules } from "@eslint/compat";

export default [
    { files: ["**/*.{js,mjs,cjs,ts,jsx,tsx}"] },
    { languageOptions: { parserOptions: { ecmaFeatures: { jsx: true } } } },
    { languageOptions: { globals: globals.browser } },
    pluginJs.configs.recommended,
    ...tseslint.configs.recommended,
    ...fixupConfigRules(pluginReactConfig),
    {
        rules: {
            "react/prop-types": "off",
            "react/react-in-jsx-scope": "off",
            "space-before-function-paren": "off",
            "no-console": "warn",
            "comma-dangle": [
                "error",
                "always-multiline",
            ],
            "jsx-quotes": [
                "error",
                "prefer-double",
            ],
        },
    },
];