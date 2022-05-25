const initialState = {
  cart: [],
};
const ADD_TO_CART = "ADD_TO_CART";

const addToCart = (item) => {
  return (dispatch) => {
    return dispatch({
      type: ADD_TO_CART,
      payload: {
        id: item?.id,
        name: item?.name,
        brand: item?.brand,
        quantity: item?.quantity,
      },
    });
  };
};

const addToCartReducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_TO_CART:
      return {
        ...state,
        cart: [
          ...state.cart,
          {
            id: action.payload.id,
            name: action.payload.name,
            brand: action.payload.brand,
            quantity: action.payload.quantity,
          },
        ],
      };
    default:
      return state;
  }
};
