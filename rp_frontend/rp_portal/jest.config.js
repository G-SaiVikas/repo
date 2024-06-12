// jest.config.js
module.exports = {
  setupFilesAfterEnv: ["<rootDir>/src/setupTests.js"],
  transform: {
    "^.+\\.(js|jsx)$": "babel-jest",
    "^.+\\.css$": "jest-transform-stub",
    "^.+\\.(jpg|jpeg|png|gif|webp|svg)$": "jest-transform-stub"
  },
  moduleNameMapper: {
    "\\.(css|less|scss|sass)$": "identity-obj-proxy"
  },
  testEnvironment: "jsdom",
  transformIgnorePatterns: [
    "/node_modules/(?!(axios)/)"
  ],
  moduleFileExtensions: ["js", "jsx", "json", "node"]
};
